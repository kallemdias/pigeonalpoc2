import { IWeatherReport } from '@/shared/model/weather-report.model';
import { ILeg } from '@/shared/model/leg.model';
import { IYcLogEntry } from '@/shared/model/yc-log-entry.model';
import { IUser } from '@/shared/model/user.model';
import { IAssociation } from '@/shared/model/association.model';

import { CheckPointDistance } from '@/shared/model/enumerations/check-point-distance.model';
import { CheckLinePointDistance } from '@/shared/model/enumerations/check-line-point-distance.model';
import { TimeWindow } from '@/shared/model/enumerations/time-window.model';
export interface IRacingPlan {
  id?: number;
  name?: string;
  assocation?: string;
  releaseDate?: Date | null;
  releasePoint?: string | null;
  arrivalPoint?: string | null;
  releasePointDMS?: string | null;
  arrivalPointDMS?: string | null;
  releaseMapLink?: string | null;
  arrivalMapLink?: string | null;
  distance?: number | null;
  distanceDisplayedValue?: string | null;
  checkPointDistance?: CheckPointDistance | null;
  checkLinedReporting?: boolean | null;
  checkLinePointDistance?: CheckLinePointDistance | null;
  timeWindow?: TimeWindow | null;
  generationInProgress?: boolean | null;
  generated?: boolean | null;
  reset?: boolean | null;
  progress?: number | null;
  weatherReports?: IWeatherReport[] | null;
  legs?: ILeg[] | null;
  logs?: IYcLogEntry[] | null;
  user?: IUser | null;
  association?: IAssociation | null;
}

export class RacingPlan implements IRacingPlan {
  constructor(
    public id?: number,
    public name?: string,
    public assocation?: string,
    public releaseDate?: Date | null,
    public releasePoint?: string | null,
    public arrivalPoint?: string | null,
    public releasePointDMS?: string | null,
    public arrivalPointDMS?: string | null,
    public releaseMapLink?: string | null,
    public arrivalMapLink?: string | null,
    public distance?: number | null,
    public distanceDisplayedValue?: string | null,
    public checkPointDistance?: CheckPointDistance | null,
    public checkLinedReporting?: boolean | null,
    public checkLinePointDistance?: CheckLinePointDistance | null,
    public timeWindow?: TimeWindow | null,
    public generationInProgress?: boolean | null,
    public generated?: boolean | null,
    public reset?: boolean | null,
    public progress?: number | null,
    public weatherReports?: IWeatherReport[] | null,
    public legs?: ILeg[] | null,
    public logs?: IYcLogEntry[] | null,
    public user?: IUser | null,
    public association?: IAssociation | null
  ) {
    this.checkLinedReporting = this.checkLinedReporting ?? false;
    this.generationInProgress = this.generationInProgress ?? false;
    this.generated = this.generated ?? false;
    this.reset = this.reset ?? false;
  }
}
