/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import WeatherReportDetailComponent from '@/entities/weather-report/weather-report-details.vue';
import WeatherReportClass from '@/entities/weather-report/weather-report-details.component';
import WeatherReportService from '@/entities/weather-report/weather-report.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('WeatherReport Management Detail Component', () => {
    let wrapper: Wrapper<WeatherReportClass>;
    let comp: WeatherReportClass;
    let weatherReportServiceStub: SinonStubbedInstance<WeatherReportService>;

    beforeEach(() => {
      weatherReportServiceStub = sinon.createStubInstance<WeatherReportService>(WeatherReportService);

      wrapper = shallowMount<WeatherReportClass>(WeatherReportDetailComponent, {
        store,
        localVue,
        router,
        provide: { weatherReportService: () => weatherReportServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundWeatherReport = { id: 123 };
        weatherReportServiceStub.find.resolves(foundWeatherReport);

        // WHEN
        comp.retrieveWeatherReport(123);
        await comp.$nextTick();

        // THEN
        expect(comp.weatherReport).toBe(foundWeatherReport);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundWeatherReport = { id: 123 };
        weatherReportServiceStub.find.resolves(foundWeatherReport);

        // WHEN
        comp.beforeRouteEnter({ params: { weatherReportId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.weatherReport).toBe(foundWeatherReport);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
