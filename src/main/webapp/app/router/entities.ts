import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

// prettier-ignore
const Association = () => import('@/entities/association/association.vue');
// prettier-ignore
const AssociationUpdate = () => import('@/entities/association/association-update.vue');
// prettier-ignore
const AssociationDetails = () => import('@/entities/association/association-details.vue');
// prettier-ignore
const RacingPlan = () => import('@/entities/racing-plan/racing-plan.vue');
// prettier-ignore
const RacingPlanUpdate = () => import('@/entities/racing-plan/racing-plan-update.vue');
// prettier-ignore
const RacingPlanDetails = () => import('@/entities/racing-plan/racing-plan-details.vue');
// prettier-ignore
const Leg = () => import('@/entities/leg/leg.vue');
// prettier-ignore
const LegUpdate = () => import('@/entities/leg/leg-update.vue');
// prettier-ignore
const LegDetails = () => import('@/entities/leg/leg-details.vue');
// prettier-ignore
const WeatherReport = () => import('@/entities/weather-report/weather-report.vue');
// prettier-ignore
const WeatherReportUpdate = () => import('@/entities/weather-report/weather-report-update.vue');
// prettier-ignore
const WeatherReportDetails = () => import('@/entities/weather-report/weather-report-details.vue');
// prettier-ignore
const CheckLine = () => import('@/entities/check-line/check-line.vue');
// prettier-ignore
const CheckLineUpdate = () => import('@/entities/check-line/check-line-update.vue');
// prettier-ignore
const CheckLineDetails = () => import('@/entities/check-line/check-line-details.vue');
// prettier-ignore
const CheckPoint = () => import('@/entities/check-point/check-point.vue');
// prettier-ignore
const CheckPointUpdate = () => import('@/entities/check-point/check-point-update.vue');
// prettier-ignore
const CheckPointDetails = () => import('@/entities/check-point/check-point-details.vue');
// prettier-ignore
const WeatherDataPoint = () => import('@/entities/weather-data-point/weather-data-point.vue');
// prettier-ignore
const WeatherDataPointUpdate = () => import('@/entities/weather-data-point/weather-data-point-update.vue');
// prettier-ignore
const WeatherDataPointDetails = () => import('@/entities/weather-data-point/weather-data-point-details.vue');
// prettier-ignore
const RelevanceHeader = () => import('@/entities/relevance-header/relevance-header.vue');
// prettier-ignore
const RelevanceHeaderUpdate = () => import('@/entities/relevance-header/relevance-header-update.vue');
// prettier-ignore
const RelevanceHeaderDetails = () => import('@/entities/relevance-header/relevance-header-details.vue');
// prettier-ignore
const GpsCoordinateCheck = () => import('@/entities/gps-coordinate-check/gps-coordinate-check.vue');
// prettier-ignore
const GpsCoordinateCheckUpdate = () => import('@/entities/gps-coordinate-check/gps-coordinate-check-update.vue');
// prettier-ignore
const GpsCoordinateCheckDetails = () => import('@/entities/gps-coordinate-check/gps-coordinate-check-details.vue');
// prettier-ignore
const DistanceCalculator = () => import('@/entities/distance-calculator/distance-calculator.vue');
// prettier-ignore
const DistanceCalculatorUpdate = () => import('@/entities/distance-calculator/distance-calculator-update.vue');
// prettier-ignore
const DistanceCalculatorDetails = () => import('@/entities/distance-calculator/distance-calculator-details.vue');
// prettier-ignore
const VelocityCalculator = () => import('@/entities/velocity-calculator/velocity-calculator.vue');
// prettier-ignore
const VelocityCalculatorUpdate = () => import('@/entities/velocity-calculator/velocity-calculator-update.vue');
// prettier-ignore
const VelocityCalculatorDetails = () => import('@/entities/velocity-calculator/velocity-calculator-details.vue');
// prettier-ignore
const YcLogEntry = () => import('@/entities/yc-log-entry/yc-log-entry.vue');
// prettier-ignore
const YcLogEntryUpdate = () => import('@/entities/yc-log-entry/yc-log-entry-update.vue');
// prettier-ignore
const YcLogEntryDetails = () => import('@/entities/yc-log-entry/yc-log-entry-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'association',
      name: 'Association',
      component: Association,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'association/new',
      name: 'AssociationCreate',
      component: AssociationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'association/:associationId/edit',
      name: 'AssociationEdit',
      component: AssociationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'association/:associationId/view',
      name: 'AssociationView',
      component: AssociationDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'racing-plan',
      name: 'RacingPlan',
      component: RacingPlan,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'racing-plan/new',
      name: 'RacingPlanCreate',
      component: RacingPlanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'racing-plan/:racingPlanId/edit',
      name: 'RacingPlanEdit',
      component: RacingPlanUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'racing-plan/:racingPlanId/view',
      name: 'RacingPlanView',
      component: RacingPlanDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'leg',
      name: 'Leg',
      component: Leg,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'leg/new',
      name: 'LegCreate',
      component: LegUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'leg/:legId/edit',
      name: 'LegEdit',
      component: LegUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'leg/:legId/view',
      name: 'LegView',
      component: LegDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'weather-report',
      name: 'WeatherReport',
      component: WeatherReport,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'weather-report/new',
      name: 'WeatherReportCreate',
      component: WeatherReportUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'weather-report/:weatherReportId/edit',
      name: 'WeatherReportEdit',
      component: WeatherReportUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'weather-report/:weatherReportId/view',
      name: 'WeatherReportView',
      component: WeatherReportDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'check-line',
      name: 'CheckLine',
      component: CheckLine,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'check-line/new',
      name: 'CheckLineCreate',
      component: CheckLineUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'check-line/:checkLineId/edit',
      name: 'CheckLineEdit',
      component: CheckLineUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'check-line/:checkLineId/view',
      name: 'CheckLineView',
      component: CheckLineDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'check-point',
      name: 'CheckPoint',
      component: CheckPoint,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'check-point/new',
      name: 'CheckPointCreate',
      component: CheckPointUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'check-point/:checkPointId/edit',
      name: 'CheckPointEdit',
      component: CheckPointUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'check-point/:checkPointId/view',
      name: 'CheckPointView',
      component: CheckPointDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'weather-data-point',
      name: 'WeatherDataPoint',
      component: WeatherDataPoint,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'weather-data-point/new',
      name: 'WeatherDataPointCreate',
      component: WeatherDataPointUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'weather-data-point/:weatherDataPointId/edit',
      name: 'WeatherDataPointEdit',
      component: WeatherDataPointUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'weather-data-point/:weatherDataPointId/view',
      name: 'WeatherDataPointView',
      component: WeatherDataPointDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'relevance-header',
      name: 'RelevanceHeader',
      component: RelevanceHeader,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'relevance-header/new',
      name: 'RelevanceHeaderCreate',
      component: RelevanceHeaderUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'relevance-header/:relevanceHeaderId/edit',
      name: 'RelevanceHeaderEdit',
      component: RelevanceHeaderUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'relevance-header/:relevanceHeaderId/view',
      name: 'RelevanceHeaderView',
      component: RelevanceHeaderDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'gps-coordinate-check',
      name: 'GpsCoordinateCheck',
      component: GpsCoordinateCheck,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'gps-coordinate-check/new',
      name: 'GpsCoordinateCheckCreate',
      component: GpsCoordinateCheckUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'gps-coordinate-check/:gpsCoordinateCheckId/edit',
      name: 'GpsCoordinateCheckEdit',
      component: GpsCoordinateCheckUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'gps-coordinate-check/:gpsCoordinateCheckId/view',
      name: 'GpsCoordinateCheckView',
      component: GpsCoordinateCheckDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'distance-calculator',
      name: 'DistanceCalculator',
      component: DistanceCalculator,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'distance-calculator/new',
      name: 'DistanceCalculatorCreate',
      component: DistanceCalculatorUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'distance-calculator/:distanceCalculatorId/edit',
      name: 'DistanceCalculatorEdit',
      component: DistanceCalculatorUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'distance-calculator/:distanceCalculatorId/view',
      name: 'DistanceCalculatorView',
      component: DistanceCalculatorDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'velocity-calculator',
      name: 'VelocityCalculator',
      component: VelocityCalculator,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'velocity-calculator/new',
      name: 'VelocityCalculatorCreate',
      component: VelocityCalculatorUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'velocity-calculator/:velocityCalculatorId/edit',
      name: 'VelocityCalculatorEdit',
      component: VelocityCalculatorUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'velocity-calculator/:velocityCalculatorId/view',
      name: 'VelocityCalculatorView',
      component: VelocityCalculatorDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'yc-log-entry',
      name: 'YcLogEntry',
      component: YcLogEntry,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'yc-log-entry/new',
      name: 'YcLogEntryCreate',
      component: YcLogEntryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'yc-log-entry/:ycLogEntryId/edit',
      name: 'YcLogEntryEdit',
      component: YcLogEntryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'yc-log-entry/:ycLogEntryId/view',
      name: 'YcLogEntryView',
      component: YcLogEntryDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
