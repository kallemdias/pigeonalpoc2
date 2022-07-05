/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import WeatherReportService from '@/entities/weather-report/weather-report.service';
import { WeatherReport } from '@/shared/model/weather-report.model';

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
  describe('WeatherReport Service', () => {
    let service: WeatherReportService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new WeatherReportService();
      currentDate = new Date();
      elemDefault = new WeatherReport(123, currentDate, currentDate, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            initiated: dayjs(currentDate).format(DATE_TIME_FORMAT),
            releaseDateTime: dayjs(currentDate).format(DATE_TIME_FORMAT),
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

      it('should create a WeatherReport', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            initiated: dayjs(currentDate).format(DATE_TIME_FORMAT),
            releaseDateTime: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            initiated: currentDate,
            releaseDateTime: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a WeatherReport', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a WeatherReport', async () => {
        const returnedFromService = Object.assign(
          {
            initiated: dayjs(currentDate).format(DATE_TIME_FORMAT),
            releaseDateTime: dayjs(currentDate).format(DATE_TIME_FORMAT),
            checkpointsDistance: 1,
            alerts: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            initiated: currentDate,
            releaseDateTime: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a WeatherReport', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a WeatherReport', async () => {
        const patchObject = Object.assign(
          {
            initiated: dayjs(currentDate).format(DATE_TIME_FORMAT),
            releaseDateTime: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          new WeatherReport()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            initiated: currentDate,
            releaseDateTime: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a WeatherReport', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of WeatherReport', async () => {
        const returnedFromService = Object.assign(
          {
            initiated: dayjs(currentDate).format(DATE_TIME_FORMAT),
            releaseDateTime: dayjs(currentDate).format(DATE_TIME_FORMAT),
            checkpointsDistance: 1,
            alerts: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            initiated: currentDate,
            releaseDateTime: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of WeatherReport', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a WeatherReport', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a WeatherReport', async () => {
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
