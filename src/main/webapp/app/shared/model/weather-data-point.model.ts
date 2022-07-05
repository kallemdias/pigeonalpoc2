import { IRelevanceHeader } from '@/shared/model/relevance-header.model';
import { ICheckPoint } from '@/shared/model/check-point.model';

import { WeatherProperty } from '@/shared/model/enumerations/weather-property.model';
export interface IWeatherDataPoint {
  id?: number;
  property?: WeatherProperty | null;
  midNight?: string | null;
  migNightRelevance?: boolean | null;
  one?: string | null;
  oneRelevance?: boolean | null;
  two?: string | null;
  twoRelevance?: boolean | null;
  three?: string | null;
  threeRelevance?: boolean | null;
  four?: string | null;
  fourRelevance?: boolean | null;
  five?: string | null;
  fiveRelevance?: boolean | null;
  six?: string | null;
  sixRelevance?: boolean | null;
  seven?: string | null;
  sevenRelevance?: boolean | null;
  eight?: string | null;
  eightRelevance?: boolean | null;
  nine?: string | null;
  nineRelevance?: boolean | null;
  ten?: string | null;
  tenRelevance?: boolean | null;
  eleven?: string | null;
  elevenRelevance?: boolean | null;
  twelve?: string | null;
  twelveRelevance?: boolean | null;
  thirteen?: string | null;
  thirteenRelevance?: boolean | null;
  fourteen?: string | null;
  fourteenRelevance?: boolean | null;
  fifteen?: string | null;
  fifteenRelevance?: boolean | null;
  sixteen?: string | null;
  sixteenRelevance?: boolean | null;
  seventeen?: string | null;
  seventeenRelevance?: boolean | null;
  eighteen?: string | null;
  eighteenRelevance?: boolean | null;
  nineteen?: string | null;
  nineteenRelevance?: boolean | null;
  twenty?: string | null;
  twentyRelevance?: boolean | null;
  twentyOne?: string | null;
  twentyOneRelevance?: boolean | null;
  twentyTwo?: string | null;
  twentyTwoRelevance?: boolean | null;
  twentyThree?: string | null;
  releavanceHeader?: IRelevanceHeader | null;
  checkPoint?: ICheckPoint | null;
}

export class WeatherDataPoint implements IWeatherDataPoint {
  constructor(
    public id?: number,
    public property?: WeatherProperty | null,
    public midNight?: string | null,
    public migNightRelevance?: boolean | null,
    public one?: string | null,
    public oneRelevance?: boolean | null,
    public two?: string | null,
    public twoRelevance?: boolean | null,
    public three?: string | null,
    public threeRelevance?: boolean | null,
    public four?: string | null,
    public fourRelevance?: boolean | null,
    public five?: string | null,
    public fiveRelevance?: boolean | null,
    public six?: string | null,
    public sixRelevance?: boolean | null,
    public seven?: string | null,
    public sevenRelevance?: boolean | null,
    public eight?: string | null,
    public eightRelevance?: boolean | null,
    public nine?: string | null,
    public nineRelevance?: boolean | null,
    public ten?: string | null,
    public tenRelevance?: boolean | null,
    public eleven?: string | null,
    public elevenRelevance?: boolean | null,
    public twelve?: string | null,
    public twelveRelevance?: boolean | null,
    public thirteen?: string | null,
    public thirteenRelevance?: boolean | null,
    public fourteen?: string | null,
    public fourteenRelevance?: boolean | null,
    public fifteen?: string | null,
    public fifteenRelevance?: boolean | null,
    public sixteen?: string | null,
    public sixteenRelevance?: boolean | null,
    public seventeen?: string | null,
    public seventeenRelevance?: boolean | null,
    public eighteen?: string | null,
    public eighteenRelevance?: boolean | null,
    public nineteen?: string | null,
    public nineteenRelevance?: boolean | null,
    public twenty?: string | null,
    public twentyRelevance?: boolean | null,
    public twentyOne?: string | null,
    public twentyOneRelevance?: boolean | null,
    public twentyTwo?: string | null,
    public twentyTwoRelevance?: boolean | null,
    public twentyThree?: string | null,
    public releavanceHeader?: IRelevanceHeader | null,
    public checkPoint?: ICheckPoint | null
  ) {
    this.migNightRelevance = this.migNightRelevance ?? false;
    this.oneRelevance = this.oneRelevance ?? false;
    this.twoRelevance = this.twoRelevance ?? false;
    this.threeRelevance = this.threeRelevance ?? false;
    this.fourRelevance = this.fourRelevance ?? false;
    this.fiveRelevance = this.fiveRelevance ?? false;
    this.sixRelevance = this.sixRelevance ?? false;
    this.sevenRelevance = this.sevenRelevance ?? false;
    this.eightRelevance = this.eightRelevance ?? false;
    this.nineRelevance = this.nineRelevance ?? false;
    this.tenRelevance = this.tenRelevance ?? false;
    this.elevenRelevance = this.elevenRelevance ?? false;
    this.twelveRelevance = this.twelveRelevance ?? false;
    this.thirteenRelevance = this.thirteenRelevance ?? false;
    this.fourteenRelevance = this.fourteenRelevance ?? false;
    this.fifteenRelevance = this.fifteenRelevance ?? false;
    this.sixteenRelevance = this.sixteenRelevance ?? false;
    this.seventeenRelevance = this.seventeenRelevance ?? false;
    this.eighteenRelevance = this.eighteenRelevance ?? false;
    this.nineteenRelevance = this.nineteenRelevance ?? false;
    this.twentyRelevance = this.twentyRelevance ?? false;
    this.twentyOneRelevance = this.twentyOneRelevance ?? false;
    this.twentyTwoRelevance = this.twentyTwoRelevance ?? false;
  }
}
