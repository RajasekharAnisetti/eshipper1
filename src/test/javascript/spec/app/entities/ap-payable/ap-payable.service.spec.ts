import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ApPayableService } from 'app/entities/ap-payable/ap-payable.service';
import { IApPayable, ApPayable } from 'app/shared/model/ap-payable.model';

describe('Service Tests', () => {
  describe('ApPayable Service', () => {
    let injector: TestBed;
    let service: ApPayableService;
    let httpMock: HttpTestingController;
    let elemDefault: IApPayable;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ApPayableService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ApPayable(0, currentDate, 0, 'AAAAAAA', 'AAAAAAA', false, false, false, 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            invoiceDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a ApPayable', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            invoiceDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            invoiceDate: currentDate
          },
          returnedFromService
        );
        service
          .create(new ApPayable(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ApPayable', () => {
        const returnedFromService = Object.assign(
          {
            invoiceDate: currentDate.format(DATE_FORMAT),
            invoiceAmount: 1,
            invoiceNo: 'BBBBBB',
            comment: 'BBBBBB',
            isCredit: true,
            isCreditNote: true,
            isDispute: true,
            docPath: 'BBBBBB',
            subTotal: 1,
            gst: 1,
            hst: 1,
            pst: 1,
            qst: 1,
            totalAmount: 1,
            balanceDue: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            invoiceDate: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of ApPayable', () => {
        const returnedFromService = Object.assign(
          {
            invoiceDate: currentDate.format(DATE_FORMAT),
            invoiceAmount: 1,
            invoiceNo: 'BBBBBB',
            comment: 'BBBBBB',
            isCredit: true,
            isCreditNote: true,
            isDispute: true,
            docPath: 'BBBBBB',
            subTotal: 1,
            gst: 1,
            hst: 1,
            pst: 1,
            qst: 1,
            totalAmount: 1,
            balanceDue: 1
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            invoiceDate: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ApPayable', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
