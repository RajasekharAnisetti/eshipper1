import { IApPayable } from 'app/shared/model/ap-payable.model';

export interface IApCategoryType {
  id?: number;
  name?: string;
  apPayables?: IApPayable[];
}

export class ApCategoryType implements IApCategoryType {
  constructor(public id?: number, public name?: string, public apPayables?: IApPayable[]) {}
}
