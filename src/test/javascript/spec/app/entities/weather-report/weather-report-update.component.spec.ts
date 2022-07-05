/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import * as config from '@/shared/config/config';
import WeatherReportUpdateComponent from '@/entities/weather-report/weather-report-update.vue';
import WeatherReportClass from '@/entities/weather-report/weather-report-update.component';
import WeatherReportService from '@/entities/weather-report/weather-report.service';

import CheckLineService from '@/entities/check-line/check-line.service';

import RacingPlanService from '@/entities/racing-plan/racing-plan.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('WeatherReport Management Update Component', () => {
    let wrapper: Wrapper<WeatherReportClass>;
    let comp: WeatherReportClass;
    let weatherReportServiceStub: SinonStubbedInstance<WeatherReportService>;

    beforeEach(() => {
      weatherReportServiceStub = sinon.createStubInstance<WeatherReportService>(WeatherReportService);

      wrapper = shallowMount<WeatherReportClass>(WeatherReportUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          weatherReportService: () => weatherReportServiceStub,
          alertService: () => new AlertService(),

          checkLineService: () =>
            sinon.createStubInstance<CheckLineService>(CheckLineService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          racingPlanService: () =>
            sinon.createStubInstance<RacingPlanService>(RacingPlanService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      });
      comp = wrapper.vm;
    });

    describe('load', () => {
      it('Should convert date from string', () => {
        // GIVEN
        const date = new Date('2019-10-15T11:42:02Z');

        // WHEN
        const convertedDate = comp.convertDateTimeFromServer(date);

        // THEN
        expect(convertedDate).toEqual(dayjs(date).format(DATE_TIME_LONG_FORMAT));
      });

      it('Should not convert date if date is not present', () => {
        expect(comp.convertDateTimeFromServer(null)).toBeNull();
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.weatherReport = entity;
        weatherReportServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(weatherReportServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.weatherReport = entity;
        weatherReportServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(weatherReportServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundWeatherReport = { id: 123 };
        weatherReportServiceStub.find.resolves(foundWeatherReport);
        weatherReportServiceStub.retrieve.resolves([foundWeatherReport]);

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
