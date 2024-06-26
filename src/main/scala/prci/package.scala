// See LICENSE.SiFive for license details.

package freechips.rocketchip

import org.chipsalliance.diplomacy.nodes._
import freechips.rocketchip.diplomacy.{BufferParams}

package object prci
{
  type ClockInwardNode = InwardNodeHandle[ClockSourceParameters, ClockSinkParameters, ClockEdgeParameters, ClockBundle]
  type ClockOutwardNode = OutwardNodeHandle[ClockSourceParameters, ClockSinkParameters, ClockEdgeParameters, ClockBundle]
  type ClockNode = NodeHandle[ClockSourceParameters, ClockSinkParameters, ClockEdgeParameters, ClockBundle, ClockSourceParameters, ClockSinkParameters, ClockEdgeParameters, ClockBundle]

  type ClockGroupNode = NodeHandle[ClockGroupSourceParameters, ClockGroupSinkParameters, ClockGroupEdgeParameters, ClockGroupBundle, ClockGroupSourceParameters, ClockGroupSinkParameters, ClockGroupEdgeParameters, ClockGroupBundle]

  def asyncMux[T](xType: ClockCrossingType, async: T, notasync: T): T = xType match {
    case _: AsynchronousCrossing => async
    case _ => notasync
  }

  implicit def noCrossing(value: NoCrossing.type): ClockCrossingType = SynchronousCrossing(BufferParams.none)
}
