/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import CheckLineDetailComponent from '@/entities/check-line/check-line-details.vue';
import CheckLineClass from '@/entities/check-line/check-line-details.component';
import CheckLineService from '@/entities/check-line/check-line.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('CheckLine Management Detail Component', () => {
    let wrapper: Wrapper<CheckLineClass>;
    let comp: CheckLineClass;
    let checkLineServiceStub: SinonStubbedInstance<CheckLineService>;

    beforeEach(() => {
      checkLineServiceStub = sinon.createStubInstance<CheckLineService>(CheckLineService);

      wrapper = shallowMount<CheckLineClass>(CheckLineDetailComponent, {
        store,
        localVue,
        router,
        provide: { checkLineService: () => checkLineServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundCheckLine = { id: 123 };
        checkLineServiceStub.find.resolves(foundCheckLine);

        // WHEN
        comp.retrieveCheckLine(123);
        await comp.$nextTick();

        // THEN
        expect(comp.checkLine).toBe(foundCheckLine);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundCheckLine = { id: 123 };
        checkLineServiceStub.find.resolves(foundCheckLine);

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
