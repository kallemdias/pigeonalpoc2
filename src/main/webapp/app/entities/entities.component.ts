import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import AssociationService from './association/association.service';
import RacingPlanService from './racing-plan/racing-plan.service';
import LegService from './leg/leg.service';
import WeatherReportService from './weather-report/weather-report.service';
import CheckLineService from './check-line/check-line.service';
import CheckPointService from './check-point/check-point.service';
import WeatherDataPointService from './weather-data-point/weather-data-point.service';
import RelevanceHeaderService from './relevance-header/relevance-header.service';
import GpsCoordinateCheckService from './gps-coordinate-check/gps-coordinate-check.service';
import DistanceCalculatorService from './distance-calculator/distance-calculator.service';
import VelocityCalculatorService from './velocity-calculator/velocity-calculator.service';
import YcLogEntryService from './yc-log-entry/yc-log-entry.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('associationService') private associationService = () => new AssociationService();
  @Provide('racingPlanService') private racingPlanService = () => new RacingPlanService();
  @Provide('legService') private legService = () => new LegService();
  @Provide('weatherReportService') private weatherReportService = () => new WeatherReportService();
  @Provide('checkLineService') private checkLineService = () => new CheckLineService();
  @Provide('checkPointService') private checkPointService = () => new CheckPointService();
  @Provide('weatherDataPointService') private weatherDataPointService = () => new WeatherDataPointService();
  @Provide('relevanceHeaderService') private relevanceHeaderService = () => new RelevanceHeaderService();
  @Provide('gpsCoordinateCheckService') private gpsCoordinateCheckService = () => new GpsCoordinateCheckService();
  @Provide('distanceCalculatorService') private distanceCalculatorService = () => new DistanceCalculatorService();
  @Provide('velocityCalculatorService') private velocityCalculatorService = () => new VelocityCalculatorService();
  @Provide('ycLogEntryService') private ycLogEntryService = () => new YcLogEntryService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
