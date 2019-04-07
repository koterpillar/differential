package com.koterpillar.differential.generic

import com.koterpillar.differential.Update
import shapeless._

sealed trait GenericPatch[Original] {
  type Repr

  def genericUpdate(original: Original, patch: Repr): Original
}

case object HNilPatch extends GenericPatch[HNil] {
  type Repr = HNil

  def genericUpdate(original: HNil, patch: Repr): HNil = HNil
}

case class HConsPatch[
HeadOriginal,
TailOriginal <: HList,
HeadPatch,
TailPatch <: HList
](
   implicit headUpdate: Update[HeadOriginal, HeadPatch],
   tailPatch: GenericPatch.Aux[TailOriginal, TailPatch]
 ) extends GenericPatch[HeadOriginal :: TailOriginal] {
  type Repr = HeadPatch :: TailPatch

  def genericUpdate(original: HeadOriginal :: TailOriginal, patch: HeadPatch :: TailPatch): HeadOriginal :: TailOriginal = (original, patch) match {
    case (oHead :: oTail, pHead :: pTail) => headUpdate(oHead, pHead) :: tailPatch.genericUpdate(oTail, pTail)
  }
}

object GenericPatch {
  type Aux[Original, Repr0] = GenericPatch[Original] {type Repr = Repr0}

  implicit def genericApply[
  Original,
  OriginalRepr
  ](
     implicit gen: Generic.Aux[Original, OriginalRepr],
     patchGen: GenericPatch.Aux[OriginalRepr, Patch]
   ): Update[Original, Patch] = (o: Original, p: Patch) => gen.from(patchGen.genericUpdate(gen.to(o), p))
}
