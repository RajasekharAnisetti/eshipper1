import { IApPayable } from 'app/shared/model/ap-payable.model';

export interface IWoPaymentMethod {
  id?: number;
  name?: string;
  apPayables?: IApPayable[];
}

export class WoPaymentMethod implements IWoPaymentMethod {
  constructor(public id?: number, public name?: string, public apPayables?: IApPayable[]) {}
}
