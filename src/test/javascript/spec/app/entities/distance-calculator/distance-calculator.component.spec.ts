/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import DistanceCalculatorComponent from '@/entities/distance-calculator/distance-calculator.vue';
import DistanceCalculatorClass from '@/entities/distance-calculator/distance-calculator.component';
import DistanceCalculatorService from '@/entities/distance-calculator/distance-calculator.service';
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
  describe('DistanceCalculator Management Component', () => {
    let wrapper: Wrapper<DistanceCalculatorClass>;
    let comp: DistanceCalculatorClass;
    let distanceCalculatorServiceStub: SinonStubbedInstance<DistanceCalculatorService>;

    beforeEach(() => {
      distanceCalculatorServiceStub = sinon.createStubInstance<DistanceCalculatorService>(DistanceCalculatorService);
      distanceCalculatorServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<DistanceCalculatorClass>(DistanceCalculatorComponent, {
        store,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          distanceCalculatorService: () => distanceCalculatorServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      distanceCalculatorServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllDistanceCalculators();
      await comp.$nextTick();

      // THEN
      expect(distanceCalculatorServiceStub.retrieve.called).toBeTruthy();
      expect(comp.distanceCalculators[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      distanceCalculatorServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(distanceCalculatorServiceStub.retrieve.called).toBeTruthy();
      expect(comp.distanceCalculators[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      distanceCalculatorServiceStub.retrieve.reset();
      distanceCalculatorServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(distanceCalculatorServiceStub.retrieve.callCount).toEqual(2);
      expect(comp.page).toEqual(1);
      expect(comp.distanceCalculators[0]).toEqual(expect.objectContaining({ id: 123 }));
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
      distanceCalculatorServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(distanceCalculatorServiceStub.retrieve.callCount).toEqual(1);

      comp.removeDistanceCalculator();
      await comp.$nextTick();

      // THEN
      expect(distanceCalculatorServiceStub.delete.called).toBeTruthy();
      expect(distanceCalculatorServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
