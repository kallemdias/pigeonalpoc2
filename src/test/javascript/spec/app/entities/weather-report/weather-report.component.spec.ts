/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import WeatherReportComponent from '@/entities/weather-report/weather-report.vue';
import WeatherReportClass from '@/entities/weather-report/weather-report.component';
import WeatherReportService from '@/entities/weather-report/weather-report.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(ToastPlugin);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.component('jhi-sort-indicator', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('WeatherReport Management Component', () => {
    let wrapper: Wrapper<WeatherReportClass>;
    let comp: WeatherReportClass;
    let weatherReportServiceStub: SinonStubbedInstance<WeatherReportService>;

    beforeEach(() => {
      weatherReportServiceStub = sinon.createStubInstance<WeatherReportService>(WeatherReportService);
      weatherReportServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<WeatherReportClass>(WeatherReportComponent, {
        store,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          weatherReportService: () => weatherReportServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      weatherReportServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllWeatherReports();
      await comp.$nextTick();

      // THEN
      expect(weatherReportServiceStub.retrieve.called).toBeTruthy();
      expect(comp.weatherReports[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      weatherReportServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(weatherReportServiceStub.retrieve.called).toBeTruthy();
      expect(comp.weatherReports[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      weatherReportServiceStub.retrieve.reset();
      weatherReportServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(weatherReportServiceStub.retrieve.callCount).toEqual(2);
      expect(comp.page).toEqual(1);
      expect(comp.weatherReports[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,asc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // GIVEN
      comp.propOrder = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,asc', 'id']);
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      weatherReportServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(weatherReportServiceStub.retrieve.callCount).toEqual(1);

      comp.removeWeatherReport();
      await comp.$nextTick();

      // THEN
      expect(weatherReportServiceStub.delete.called).toBeTruthy();
      expect(weatherReportServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
