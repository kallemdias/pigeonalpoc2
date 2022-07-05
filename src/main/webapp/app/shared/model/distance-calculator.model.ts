import { LatDirection } from '@/shared/model/enumerations/lat-direction.model';
import { LngDirection } from '@/shared/model/enumerations/lng-direction.model';
export interface IDistanceCalculator {
  id?: number;
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
  distanceInMeters?: number | null;
  distanceInMetersDispVal?: string | null;
  depLink?: string | null;
  arrLink?: string | null;
}

export class DistanceCalculator implements IDistanceCalculator {
  constructor(
    public id?: number,
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
    public distanceInMeters?: number | null,
    public distanceInMetersDispVal?: string | null,
    public depLink?: string | null,
    public arrLink?: string | null
  ) {}
}
