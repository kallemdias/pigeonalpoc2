/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import RacingPlanService from '@/entities/racing-plan/racing-plan.service';
import { RacingPlan } from '@/shared/model/racing-plan.model';
import { CheckPointDistance } from '@/shared/model/enumerations/check-point-distance.model';
import { CheckLinePointDistance } from '@/shared/model/enumerations/check-line-point-distance.model';
import { TimeWindow } from '@/shared/model/enumerations/time-window.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('RacingPlan Service', () => {
    let service: RacingPlanService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new RacingPlanService();
      currentDate = new Date();
      elemDefault = new RacingPlan(
        123,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        CheckPointDistance.ONEKM,
        false,
        CheckLinePointDistance.FIVEKM,
        TimeWindow.THREEH,
        false,
        false,
        false,
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            releaseDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a RacingPlan', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            releaseDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            releaseDate: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a RacingPlan', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a RacingPlan', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            assocation: 'BBBBBB',
            releaseDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            releasePoint: 'BBBBBB',
            arrivalPoint: 'BBBBBB',
            releasePointDMS: 'BBBBBB',
            arrivalPointDMS: 'BBBBBB',
            releaseMapLink: 'BBBBBB',
            arrivalMapLink: 'BBBBBB',
            distance: 1,
            distanceDisplayedValue: 'BBBBBB',
            checkPointDistance: 'BBBBBB',
            checkLinedReporting: true,
            checkLinePointDistance: 'BBBBBB',
            timeWindow: 'BBBBBB',
            generationInProgress: true,
            generated: true,
            reset: true,
            progress: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            releaseDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a RacingPlan', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a RacingPlan', async () => {
        const patchObject = Object.assign(
          {
            name: 'BBBBBB',
            assocation: 'BBBBBB',
            releaseDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            releasePointDMS: 'BBBBBB',
            arrivalPointDMS: 'BBBBBB',
            arrivalMapLink: 'BBBBBB',
            distance: 1,
            checkLinedReporting: true,
            generationInProgress: true,
            generated: true,
            reset: true,
          },
          new RacingPlan()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            releaseDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a RacingPlan', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of RacingPlan', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            assocation: 'BBBBBB',
            releaseDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            releasePoint: 'BBBBBB',
            arrivalPoint: 'BBBBBB',
            releasePointDMS: 'BBBBBB',
            arrivalPointDMS: 'BBBBBB',
            releaseMapLink: 'BBBBBB',
            arrivalMapLink: 'BBBBBB',
            distance: 1,
            distanceDisplayedValue: 'BBBBBB',
            checkPointDistance: 'BBBBBB',
            checkLinedReporting: true,
            checkLinePointDistance: 'BBBBBB',
            timeWindow: 'BBBBBB',
            generationInProgress: true,
            generated: true,
            reset: true,
            progress: 1,
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            releaseDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of RacingPlan', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a RacingPlan', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a RacingPlan', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
