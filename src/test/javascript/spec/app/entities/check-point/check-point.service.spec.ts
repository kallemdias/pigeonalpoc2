/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import CheckPointService from '@/entities/check-point/check-point.service';
import { CheckPoint } from '@/shared/model/check-point.model';

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
  describe('CheckPoint Service', () => {
    let service: CheckPointService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new CheckPointService();
      currentDate = new Date();
      elemDefault = new CheckPoint(123, 0, currentDate, 'AAAAAAA', 0, 0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            dateTime: dayjs(currentDate).format(DATE_TIME_FORMAT),
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

      it('should create a CheckPoint', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            dateTime: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateTime: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a CheckPoint', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a CheckPoint', async () => {
        const returnedFromService = Object.assign(
          {
            order: 1,
            dateTime: dayjs(currentDate).format(DATE_TIME_FORMAT),
            locationName: 'BBBBBB',
            latDecimal: 1,
            lngDecimal: 1,
            distance: 1,
            distanceDisplayedValue: 'BBBBBB',
            link: 'BBBBBB',
            alerts: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateTime: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a CheckPoint', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a CheckPoint', async () => {
        const patchObject = Object.assign(
          {
            locationName: 'BBBBBB',
            latDecimal: 1,
            distance: 1,
            link: 'BBBBBB',
            alerts: 'BBBBBB',
          },
          new CheckPoint()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            dateTime: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a CheckPoint', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of CheckPoint', async () => {
        const returnedFromService = Object.assign(
          {
            order: 1,
            dateTime: dayjs(currentDate).format(DATE_TIME_FORMAT),
            locationName: 'BBBBBB',
            latDecimal: 1,
            lngDecimal: 1,
            distance: 1,
            distanceDisplayedValue: 'BBBBBB',
            link: 'BBBBBB',
            alerts: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateTime: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of CheckPoint', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a CheckPoint', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a CheckPoint', async () => {
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
