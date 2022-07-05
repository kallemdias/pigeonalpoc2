/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import YcLogEntryDetailComponent from '@/entities/yc-log-entry/yc-log-entry-details.vue';
import YcLogEntryClass from '@/entities/yc-log-entry/yc-log-entry-details.component';
import YcLogEntryService from '@/entities/yc-log-entry/yc-log-entry.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('YcLogEntry Management Detail Component', () => {
    let wrapper: Wrapper<YcLogEntryClass>;
    let comp: YcLogEntryClass;
    let ycLogEntryServiceStub: SinonStubbedInstance<YcLogEntryService>;

    beforeEach(() => {
      ycLogEntryServiceStub = sinon.createStubInstance<YcLogEntryService>(YcLogEntryService);

      wrapper = shallowMount<YcLogEntryClass>(YcLogEntryDetailComponent, {
        store,
        localVue,
        router,
        provide: { ycLogEntryService: () => ycLogEntryServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundYcLogEntry = { id: 123 };
        ycLogEntryServiceStub.find.resolves(foundYcLogEntry);

        // WHEN
        comp.retrieveYcLogEntry(123);
        await comp.$nextTick();

        // THEN
        expect(comp.ycLogEntry).toBe(foundYcLogEntry);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundYcLogEntry = { id: 123 };
        ycLogEntryServiceStub.find.resolves(foundYcLogEntry);

        // WHEN
        comp.beforeRouteEnter({ params: { ycLogEntryId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.ycLogEntry).toBe(foundYcLogEntry);
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
