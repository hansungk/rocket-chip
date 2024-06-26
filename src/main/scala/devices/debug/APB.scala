// See LICENSE.SiFive for license details.

package freechips.rocketchip.devices.debug

import org.chipsalliance.cde.config._
import org.chipsalliance.diplomacy.lazymodule._

import freechips.rocketchip.amba.apb.APBRegisterNode
import freechips.rocketchip.diplomacy.AddressSet
import freechips.rocketchip.regmapper.RegField

case object APBDebugRegistersKey extends Field[Map[Int, Seq[RegField]]](Map())

object APBDebugConsts {
  def apbDebugRegBase = 0xF00
  def apbDebugRegSize = 0x100
}

class APBDebugRegisters()(implicit p: Parameters) extends LazyModule {

  val node = APBRegisterNode(
    address = AddressSet(base=APBDebugConsts.apbDebugRegBase, mask=APBDebugConsts.apbDebugRegSize-1),
    beatBytes = 4,
    executable = false
  )

  lazy val module = new Impl
  class Impl extends LazyModuleImp(this){
    node.regmap(p(APBDebugRegistersKey).toList:_*)

  }
}


