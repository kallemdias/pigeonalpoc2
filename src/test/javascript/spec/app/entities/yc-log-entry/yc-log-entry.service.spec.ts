/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import YcLogEntryService from '@/entities/yc-log-entry/yc-log-entry.service';
import { YcLogEntry } from '@/shared/model/yc-log-entry.model';
import { LogType } from '@/shared/model/enumerations/log-type.model';

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
  describe('YcLogEntry Service', () => {
    let service: YcLogEntryService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new YcLogEntryService();
      currentDate = new Date();
      elemDefault = new YcLogEntry(123, currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', LogType.INFO);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            created: dayjs(currentDate).format(DATE_TIME_FORMAT),
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

      it('should create a YcLogEntry', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            created: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            created: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a YcLogEntry', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a YcLogEntry', async () => {
        const returnedFromService = Object.assign(
          {
            created: dayjs(currentDate).format(DATE_TIME_FORMAT),
            serviceName: 'BBBBBB',
            method: 'BBBBBB',
            step: 'BBBBBB',
            discription: 'BBBBBB',
            logType: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            created: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a YcLogEntry', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a YcLogEntry', async () => {
        const patchObject = Object.assign(
          {
            serviceName: 'BBBBBB',
            method: 'BBBBBB',
            step: 'BBBBBB',
            logType: 'BBBBBB',
          },
          new YcLogEntry()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            created: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a YcLogEntry', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of YcLogEntry', async () => {
        const returnedFromService = Object.assign(
          {
            created: dayjs(currentDate).format(DATE_TIME_FORMAT),
            serviceName: 'BBBBBB',
            method: 'BBBBBB',
            step: 'BBBBBB',
            discription: 'BBBBBB',
            logType: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            created: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of YcLogEntry', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a YcLogEntry', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a YcLogEntry', async () => {
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
