import { ICheckLine } from '@/shared/model/check-line.model';
import { IRacingPlan } from '@/shared/model/racing-plan.model';

export interface IWeatherReport {
  id?: number;
  initiated?: Date | null;
  releaseDateTime?: Date | null;
  checkpointsDistance?: number | null;
  alerts?: string | null;
  checkLines?: ICheckLine[] | null;
  racingPlan?: IRacingPlan | null;
}

export class WeatherReport implements IWeatherReport {
  constructor(
    public id?: number,
    public initiated?: Date | null,
    public releaseDateTime?: Date | null,
    public checkpointsDistance?: number | null,
    public alerts?: string | null,
    public checkLines?: ICheckLine[] | null,
    public racingPlan?: IRacingPlan | null
  ) {}
}
