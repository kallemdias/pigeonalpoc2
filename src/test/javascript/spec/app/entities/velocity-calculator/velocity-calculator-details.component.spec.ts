/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import VelocityCalculatorDetailComponent from '@/entities/velocity-calculator/velocity-calculator-details.vue';
import VelocityCalculatorClass from '@/entities/velocity-calculator/velocity-calculator-details.component';
import VelocityCalculatorService from '@/entities/velocity-calculator/velocity-calculator.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('VelocityCalculator Management Detail Component', () => {
    let wrapper: Wrapper<VelocityCalculatorClass>;
    let comp: VelocityCalculatorClass;
    let velocityCalculatorServiceStub: SinonStubbedInstance<VelocityCalculatorService>;

    beforeEach(() => {
      velocityCalculatorServiceStub = sinon.createStubInstance<VelocityCalculatorService>(VelocityCalculatorService);

      wrapper = shallowMount<VelocityCalculatorClass>(VelocityCalculatorDetailComponent, {
        store,
        localVue,
        router,
        provide: { velocityCalculatorService: () => velocityCalculatorServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundVelocityCalculator = { id: 123 };
        velocityCalculatorServiceStub.find.resolves(foundVelocityCalculator);

        // WHEN
        comp.retrieveVelocityCalculator(123);
        await comp.$nextTick();

        // THEN
        expect(comp.velocityCalculator).toBe(foundVelocityCalculator);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundVelocityCalculator = { id: 123 };
        velocityCalculatorServiceStub.find.resolves(foundVelocityCalculator);

        // WHEN
        comp.beforeRouteEnter({ params: { velocityCalculatorId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.velocityCalculator).toBe(foundVelocityCalculator);
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
