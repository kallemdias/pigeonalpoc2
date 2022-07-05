/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import VelocityCalculatorComponent from '@/entities/velocity-calculator/velocity-calculator.vue';
import VelocityCalculatorClass from '@/entities/velocity-calculator/velocity-calculator.component';
import VelocityCalculatorService from '@/entities/velocity-calculator/velocity-calculator.service';
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
  describe('VelocityCalculator Management Component', () => {
    let wrapper: Wrapper<VelocityCalculatorClass>;
    let comp: VelocityCalculatorClass;
    let velocityCalculatorServiceStub: SinonStubbedInstance<VelocityCalculatorService>;

    beforeEach(() => {
      velocityCalculatorServiceStub = sinon.createStubInstance<VelocityCalculatorService>(VelocityCalculatorService);
      velocityCalculatorServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<VelocityCalculatorClass>(VelocityCalculatorComponent, {
        store,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          velocityCalculatorService: () => velocityCalculatorServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      velocityCalculatorServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllVelocityCalculators();
      await comp.$nextTick();

      // THEN
      expect(velocityCalculatorServiceStub.retrieve.called).toBeTruthy();
      expect(comp.velocityCalculators[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      velocityCalculatorServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(velocityCalculatorServiceStub.retrieve.called).toBeTruthy();
      expect(comp.velocityCalculators[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      velocityCalculatorServiceStub.retrieve.reset();
      velocityCalculatorServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(velocityCalculatorServiceStub.retrieve.callCount).toEqual(2);
      expect(comp.page).toEqual(1);
      expect(comp.velocityCalculators[0]).toEqual(expect.objectContaining({ id: 123 }));
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
      velocityCalculatorServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(velocityCalculatorServiceStub.retrieve.callCount).toEqual(1);

      comp.removeVelocityCalculator();
      await comp.$nextTick();

      // THEN
      expect(velocityCalculatorServiceStub.delete.called).toBeTruthy();
      expect(velocityCalculatorServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
