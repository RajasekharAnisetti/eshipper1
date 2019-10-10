import { Moment } from 'moment';
import { IApPayableCreditNotesTrans } from 'app/shared/model/ap-payable-credit-notes-trans.model';

export interface IApPayable {
  id?: number;
  invoiceDate?: Moment;
  invoiceAmount?: number;
  invoiceNo?: string;
  comment?: string;
  isCredit?: boolean;
  isCreditNote?: boolean;
  isDispute?: boolean;
  docPath?: string;
  subTotal?: number;
  gst?: number;
  hst?: number;
  pst?: number;
  qst?: number;
  totalAmount?: number;
  balanceDue?: number;
  apPayableCreditNotesTrans?: IApPayableCreditNotesTrans[];
  creditUsedFrmAPPayables?: IApPayableCreditNotesTrans[];
  apPayeeTypeId?: number;
  apPayeeId?: number;
  apCategoryTypeId?: number;
  woPaymentMethodId?: number;
}

export class ApPayable implements IApPayable {
  constructor(
    public id?: number,
    public invoiceDate?: Moment,
    public invoiceAmount?: number,
    public invoiceNo?: string,
    public comment?: string,
    public isCredit?: boolean,
    public isCreditNote?: boolean,
    public isDispute?: boolean,
    public docPath?: string,
    public subTotal?: number,
    public gst?: number,
    public hst?: number,
    public pst?: number,
    public qst?: number,
    public totalAmount?: number,
    public balanceDue?: number,
    public apPayableCreditNotesTrans?: IApPayableCreditNotesTrans[],
    public creditUsedFrmAPPayables?: IApPayableCreditNotesTrans[],
    public apPayeeTypeId?: number,
    public apPayeeId?: number,
    public apCategoryTypeId?: number,
    public woPaymentMethodId?: number
  ) {
    this.isCredit = this.isCredit || false;
    this.isCreditNote = this.isCreditNote || false;
    this.isDispute = this.isDispute || false;
  }
}
