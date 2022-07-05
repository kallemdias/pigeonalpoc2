import { IRacingPlan } from '@/shared/model/racing-plan.model';

import { LatDirection } from '@/shared/model/enumerations/lat-direction.model';
import { LngDirection } from '@/shared/model/enumerations/lng-direction.model';
import { CheckPointDistance } from '@/shared/model/enumerations/check-point-distance.model';
import { CheckLinePointDistance } from '@/shared/model/enumerations/check-line-point-distance.model';
import { TimeWindow } from '@/shared/model/enumerations/time-window.model';
export interface ILeg {
  id?: number;
  order?: number | null;
  depLatDeg?: number | null;
  depLatMin?: number | null;
  depLatSec?: number | null;
  depLatDirection?: LatDirection | null;
  depLngDeg?: number | null;
  depLngMin?: number | null;
  depLngSec?: number | null;
  depLngDirection?: LngDirection | null;
  depLatDisplayedValue?: string | null;
  depLngDisplayedValue?: string | null;
  depLatDecimal?: number | null;
  depLngDecimal?: number | null;
  arrLatDeg?: number | null;
  arrLatMin?: number | null;
  arrLatSec?: number | null;
  arrLatDirection?: LatDirection | null;
  arrLngDeg?: number | null;
  arrLngMin?: number | null;
  arrLngSec?: number | null;
  arrLngDirection?: LngDirection | null;
  arrLatDisplayedValue?: string | null;
  arrLngDisplayedValue?: string | null;
  arrLatDecimal?: number | null;
  arrLngDecimal?: number | null;
  checkPointDistance?: CheckPointDistance | null;
  checkLinePointDistance?: CheckLinePointDistance | null;
  timeWindow?: TimeWindow | null;
  racingPlan?: IRacingPlan | null;
}

export class Leg implements ILeg {
  constructor(
    public id?: number,
    public order?: number | null,
    public depLatDeg?: number | null,
    public depLatMin?: number | null,
    public depLatSec?: number | null,
    public depLatDirection?: LatDirection | null,
    public depLngDeg?: number | null,
    public depLngMin?: number | null,
    public depLngSec?: number | null,
    public depLngDirection?: LngDirection | null,
    public depLatDisplayedValue?: string | null,
    public depLngDisplayedValue?: string | null,
    public depLatDecimal?: number | null,
    public depLngDecimal?: number | null,
    public arrLatDeg?: number | null,
    public arrLatMin?: number | null,
    public arrLatSec?: number | null,
    public arrLatDirection?: LatDirection | null,
    public arrLngDeg?: number | null,
    public arrLngMin?: number | null,
    public arrLngSec?: number | null,
    public arrLngDirection?: LngDirection | null,
    public arrLatDisplayedValue?: string | null,
    public arrLngDisplayedValue?: string | null,
    public arrLatDecimal?: number | null,
    public arrLngDecimal?: number | null,
    public checkPointDistance?: CheckPointDistance | null,
    public checkLinePointDistance?: CheckLinePointDistance | null,
    public timeWindow?: TimeWindow | null,
    public racingPlan?: IRacingPlan | null
  ) {}
}
