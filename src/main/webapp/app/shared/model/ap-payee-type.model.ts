import { IApPayable } from 'app/shared/model/ap-payable.model';

export interface IApPayeeType {
  id?: number;
  name?: string;
  apPayables?: IApPayable[];
}

export class ApPayeeType implements IApPayeeType {
  constructor(public id?: number, public name?: string, public apPayables?: IApPayable[]) {}
}
