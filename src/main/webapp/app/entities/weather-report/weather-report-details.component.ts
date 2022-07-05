import { Component, Vue, Inject } from 'vue-property-decorator';

import { IWeatherReport } from '@/shared/model/weather-report.model';
import WeatherReportService from './weather-report.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class WeatherReportDetails extends Vue {
  @Inject('weatherReportService') private weatherReportService: () => WeatherReportService;
  @Inject('alertService') private alertService: () => AlertService;

  public weatherReport: IWeatherReport = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.weatherReportId) {
        vm.retrieveWeatherReport(to.params.weatherReportId);
      }
    });
  }

  public retrieveWeatherReport(weatherReportId) {
    this.weatherReportService()
      .find(weatherReportId)
      .then(res => {
        this.weatherReport = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
