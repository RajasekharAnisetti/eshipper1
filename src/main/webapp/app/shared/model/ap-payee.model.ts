import { IApPayable } from 'app/shared/model/ap-payable.model';
import { IApPayableCreditNotesTrans } from 'app/shared/model/ap-payable-credit-notes-trans.model';

export interface IApPayee {
  id?: number;
  name?: string;
  apPayables?: IApPayable[];
  apPayableCreditNotesTrans?: IApPayableCreditNotesTrans[];
}

export class ApPayee implements IApPayee {
  constructor(
    public id?: number,
    public name?: string,
    public apPayables?: IApPayable[],
    public apPayableCreditNotesTrans?: IApPayableCreditNotesTrans[]
  ) {}
}
