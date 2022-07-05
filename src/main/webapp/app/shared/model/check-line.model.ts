import { ICheckPoint } from '@/shared/model/check-point.model';
import { IWeatherReport } from '@/shared/model/weather-report.model';

export interface ICheckLine {
  id?: number;
  order?: number | null;
  dateTime?: Date | null;
  locationName?: string | null;
  latDecimal?: number | null;
  lngDecimal?: number | null;
  distance?: number | null;
  distanceDisplayedValue?: string | null;
  link?: string | null;
  checkPoints?: ICheckPoint[] | null;
  weatherReport?: IWeatherReport | null;
}

export class CheckLine implements ICheckLine {
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
    public checkPoints?: ICheckPoint[] | null,
    public weatherReport?: IWeatherReport | null
  ) {}
}
