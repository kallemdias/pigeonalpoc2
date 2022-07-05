/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import * as config from '@/shared/config/config';
import CheckLineUpdateComponent from '@/entities/check-line/check-line-update.vue';
import CheckLineClass from '@/entities/check-line/check-line-update.component';
import CheckLineService from '@/entities/check-line/check-line.service';

import CheckPointService from '@/entities/check-point/check-point.service';

import WeatherReportService from '@/entities/weather-report/weather-report.service';
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
  describe('CheckLine Management Update Component', () => {
    let wrapper: Wrapper<CheckLineClass>;
    let comp: CheckLineClass;
    let checkLineServiceStub: SinonStubbedInstance<CheckLineService>;

    beforeEach(() => {
      checkLineServiceStub = sinon.createStubInstance<CheckLineService>(CheckLineService);

      wrapper = shallowMount<CheckLineClass>(CheckLineUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          checkLineService: () => checkLineServiceStub,
          alertService: () => new AlertService(),

          checkPointService: () =>
            sinon.createStubInstance<CheckPointService>(CheckPointService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          weatherReportService: () =>
            sinon.createStubInstance<WeatherReportService>(WeatherReportService, {
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
        comp.checkLine = entity;
        checkLineServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(checkLineServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.checkLine = entity;
        checkLineServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(checkLineServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundCheckLine = { id: 123 };
        checkLineServiceStub.find.resolves(foundCheckLine);
        checkLineServiceStub.retrieve.resolves([foundCheckLine]);

        // WHEN
        comp.beforeRouteEnter({ params: { checkLineId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.checkLine).toBe(foundCheckLine);
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
