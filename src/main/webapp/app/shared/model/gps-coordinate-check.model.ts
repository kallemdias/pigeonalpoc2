import { LatDirection } from '@/shared/model/enumerations/lat-direction.model';
import { LngDirection } from '@/shared/model/enumerations/lng-direction.model';
export interface IGpsCoordinateCheck {
  id?: number;
  latDeg?: number | null;
  latMin?: number | null;
  latSec?: number | null;
  latDirection?: LatDirection | null;
  lngDeg?: number | null;
  lngMin?: number | null;
  lngSec?: number | null;
  lngDirection?: LngDirection | null;
  latDisplayedValue?: string | null;
  lngDisplayedValue?: string | null;
  latDecimal?: number | null;
  lngDecimal?: number | null;
  link?: string | null;
}

export class GpsCoordinateCheck implements IGpsCoordinateCheck {
  constructor(
    public id?: number,
    public latDeg?: number | null,
    public latMin?: number | null,
    public latSec?: number | null,
    public latDirection?: LatDirection | null,
    public lngDeg?: number | null,
    public lngMin?: number | null,
    public lngSec?: number | null,
    public lngDirection?: LngDirection | null,
    public latDisplayedValue?: string | null,
    public lngDisplayedValue?: string | null,
    public latDecimal?: number | null,
    public lngDecimal?: number | null,
    public link?: string | null
  ) {}
}
