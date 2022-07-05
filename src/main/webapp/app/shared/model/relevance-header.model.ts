import { IWeatherDataPoint } from '@/shared/model/weather-data-point.model';

export interface IRelevanceHeader {
  id?: number;
  midnight?: boolean | null;
  one?: boolean | null;
  two?: boolean | null;
  three?: boolean | null;
  four?: boolean | null;
  five?: boolean | null;
  six?: boolean | null;
  seven?: boolean | null;
  eight?: boolean | null;
  nine?: boolean | null;
  ten?: boolean | null;
  eleven?: boolean | null;
  twelve?: boolean | null;
  thirteen?: boolean | null;
  fourteen?: boolean | null;
  fifteen?: boolean | null;
  sixteen?: boolean | null;
  seventeen?: boolean | null;
  eighteen?: boolean | null;
  nineteen?: boolean | null;
  twenty?: boolean | null;
  twentyOne?: boolean | null;
  twentyTwo?: boolean | null;
  twentyThree?: boolean | null;
  weatherDataPoint?: IWeatherDataPoint | null;
}

export class RelevanceHeader implements IRelevanceHeader {
  constructor(
    public id?: number,
    public midnight?: boolean | null,
    public one?: boolean | null,
    public two?: boolean | null,
    public three?: boolean | null,
    public four?: boolean | null,
    public five?: boolean | null,
    public six?: boolean | null,
    public seven?: boolean | null,
    public eight?: boolean | null,
    public nine?: boolean | null,
    public ten?: boolean | null,
    public eleven?: boolean | null,
    public twelve?: boolean | null,
    public thirteen?: boolean | null,
    public fourteen?: boolean | null,
    public fifteen?: boolean | null,
    public sixteen?: boolean | null,
    public seventeen?: boolean | null,
    public eighteen?: boolean | null,
    public nineteen?: boolean | null,
    public twenty?: boolean | null,
    public twentyOne?: boolean | null,
    public twentyTwo?: boolean | null,
    public twentyThree?: boolean | null,
    public weatherDataPoint?: IWeatherDataPoint | null
  ) {
    this.midnight = this.midnight ?? false;
    this.one = this.one ?? false;
    this.two = this.two ?? false;
    this.three = this.three ?? false;
    this.four = this.four ?? false;
    this.five = this.five ?? false;
    this.six = this.six ?? false;
    this.seven = this.seven ?? false;
    this.eight = this.eight ?? false;
    this.nine = this.nine ?? false;
    this.ten = this.ten ?? false;
    this.eleven = this.eleven ?? false;
    this.twelve = this.twelve ?? false;
    this.thirteen = this.thirteen ?? false;
    this.fourteen = this.fourteen ?? false;
    this.fifteen = this.fifteen ?? false;
    this.sixteen = this.sixteen ?? false;
    this.seventeen = this.seventeen ?? false;
    this.eighteen = this.eighteen ?? false;
    this.nineteen = this.nineteen ?? false;
    this.twenty = this.twenty ?? false;
    this.twentyOne = this.twentyOne ?? false;
    this.twentyTwo = this.twentyTwo ?? false;
    this.twentyThree = this.twentyThree ?? false;
  }
}
