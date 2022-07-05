/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import YcLogEntryComponent from '@/entities/yc-log-entry/yc-log-entry.vue';
import YcLogEntryClass from '@/entities/yc-log-entry/yc-log-entry.component';
import YcLogEntryService from '@/entities/yc-log-entry/yc-log-entry.service';
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
  describe('YcLogEntry Management Component', () => {
    let wrapper: Wrapper<YcLogEntryClass>;
    let comp: YcLogEntryClass;
    let ycLogEntryServiceStub: SinonStubbedInstance<YcLogEntryService>;

    beforeEach(() => {
      ycLogEntryServiceStub = sinon.createStubInstance<YcLogEntryService>(YcLogEntryService);
      ycLogEntryServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<YcLogEntryClass>(YcLogEntryComponent, {
        store,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          ycLogEntryService: () => ycLogEntryServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      ycLogEntryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllYcLogEntrys();
      await comp.$nextTick();

      // THEN
      expect(ycLogEntryServiceStub.retrieve.called).toBeTruthy();
      expect(comp.ycLogEntries[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      ycLogEntryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(ycLogEntryServiceStub.retrieve.called).toBeTruthy();
      expect(comp.ycLogEntries[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      ycLogEntryServiceStub.retrieve.reset();
      ycLogEntryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(ycLogEntryServiceStub.retrieve.callCount).toEqual(2);
      expect(comp.page).toEqual(1);
      expect(comp.ycLogEntries[0]).toEqual(expect.objectContaining({ id: 123 }));
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
      ycLogEntryServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(ycLogEntryServiceStub.retrieve.callCount).toEqual(1);

      comp.removeYcLogEntry();
      await comp.$nextTick();

      // THEN
      expect(ycLogEntryServiceStub.delete.called).toBeTruthy();
      expect(ycLogEntryServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
