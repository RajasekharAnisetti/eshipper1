export interface IApPayableCreditNotesTrans {
  id?: number;
  type?: string;
  amount?: number;
  apPayableId?: number;
  apPayeeId?: number;
  apPayableId?: number;
}

export class ApPayableCreditNotesTrans implements IApPayableCreditNotesTrans {
  constructor(
    public id?: number,
    public type?: string,
    public amount?: number,
    public apPayableId?: number,
    public apPayeeId?: number,
    public apPayableId?: number
  ) {}
}
