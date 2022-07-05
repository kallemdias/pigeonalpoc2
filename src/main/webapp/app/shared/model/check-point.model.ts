import { IWeatherDataPoint } from '@/shared/model/weather-data-point.model';
import { ICheckLine } from '@/shared/model/check-line.model';

export interface ICheckPoint {
  id?: number;
  order?: number | null;
  dateTime?: Date | null;
  locationName?: string | null;
  latDecimal?: number | null;
  lngDecimal?: number | null;
  distance?: number | null;
  distanceDisplayedValue?: string | null;
  link?: string | null;
  alerts?: string | null;
  dataPoints?: IWeatherDataPoint[] | null;
  checkLine?: ICheckLine | null;
}

export class CheckPoint implements ICheckPoint {
  constructor(
    public id?: number,
    public order?: number | null,
    public dateTime?: Date | null,
    public locationName?: string | null,
    public latDecimal?: number | null,
    public lngDecimal?: number | null,
    public distance?: number | null,
    public distanceDisplayedValue?: string | null,
    public link?: string | null,
    public alerts?: string | null,
    public dataPoints?: IWeatherDataPoint[] | null,
    public checkLine?: ICheckLine | null
  ) {}
}
