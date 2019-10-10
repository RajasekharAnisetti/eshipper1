import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IApPayable, ApPayable } from 'app/shared/model/ap-payable.model';
import { ApPayableService } from './ap-payable.service';
import { IApPayeeType } from 'app/shared/model/ap-payee-type.model';
import { ApPayeeTypeService } from 'app/entities/ap-payee-type/ap-payee-type.service';
import { IApPayee } from 'app/shared/model/ap-payee.model';
import { ApPayeeService } from 'app/entities/ap-payee/ap-payee.service';
import { IApCategoryType } from 'app/shared/model/ap-category-type.model';
import { ApCategoryTypeService } from 'app/entities/ap-category-type/ap-category-type.service';
import { IWoPaymentMethod } from 'app/shared/model/wo-payment-method.model';
import { WoPaymentMethodService } from 'app/entities/wo-payment-method/wo-payment-method.service';

@Component({
  selector: 'jhi-ap-payable-update',
  templateUrl: './ap-payable-update.component.html'
})
export class ApPayableUpdateComponent implements OnInit {
  isSaving: boolean;

  appayeetypes: IApPayeeType[];

  appayees: IApPayee[];

  apcategorytypes: IApCategoryType[];

  wopaymentmethods: IWoPaymentMethod[];
  invoiceDateDp: any;

  editForm = this.fb.group({
    id: [],
    invoiceDate: [],
    invoiceAmount: [null, [Validators.max(11)]],
    invoiceNo: [null, [Validators.maxLength(255)]],
    comment: [null, [Validators.maxLength(1000)]],
    isCredit: [],
    isCreditNote: [],
    isDispute: [],
    docPath: [null, [Validators.maxLength(255)]],
    subTotal: [],
    gst: [],
    hst: [],
    pst: [],
    qst: [],
    totalAmount: [],
    balanceDue: [],
    apPayeeTypeId: [],
    apPayeeId: [],
    apCategoryTypeId: [],
    woPaymentMethodId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected apPayableService: ApPayableService,
    protected apPayeeTypeService: ApPayeeTypeService,
    protected apPayeeService: ApPayeeService,
    protected apCategoryTypeService: ApCategoryTypeService,
    protected woPaymentMethodService: WoPaymentMethodService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ apPayable }) => {
      this.updateForm(apPayable);
    });
    this.apPayeeTypeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IApPayeeType[]>) => mayBeOk.ok),
        map((response: HttpResponse<IApPayeeType[]>) => response.body)
      )
      .subscribe((res: IApPayeeType[]) => (this.appayeetypes = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.apPayeeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IApPayee[]>) => mayBeOk.ok),
        map((response: HttpResponse<IApPayee[]>) => response.body)
      )
      .subscribe((res: IApPayee[]) => (this.appayees = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.apCategoryTypeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IApCategoryType[]>) => mayBeOk.ok),
        map((response: HttpResponse<IApCategoryType[]>) => response.body)
      )
      .subscribe((res: IApCategoryType[]) => (this.apcategorytypes = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.woPaymentMethodService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IWoPaymentMethod[]>) => mayBeOk.ok),
        map((response: HttpResponse<IWoPaymentMethod[]>) => response.body)
      )
      .subscribe((res: IWoPaymentMethod[]) => (this.wopaymentmethods = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(apPayable: IApPayable) {
    this.editForm.patchValue({
      id: apPayable.id,
      invoiceDate: apPayable.invoiceDate,
      invoiceAmount: apPayable.invoiceAmount,
      invoiceNo: apPayable.invoiceNo,
      comment: apPayable.comment,
      isCredit: apPayable.isCredit,
      isCreditNote: apPayable.isCreditNote,
      isDispute: apPayable.isDispute,
      docPath: apPayable.docPath,
      subTotal: apPayable.subTotal,
      gst: apPayable.gst,
      hst: apPayable.hst,
      pst: apPayable.pst,
      qst: apPayable.qst,
      totalAmount: apPayable.totalAmount,
      balanceDue: apPayable.balanceDue,
      apPayeeTypeId: apPayable.apPayeeTypeId,
      apPayeeId: apPayable.apPayeeId,
      apCategoryTypeId: apPayable.apCategoryTypeId,
      woPaymentMethodId: apPayable.woPaymentMethodId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const apPayable = this.createFromForm();
    if (apPayable.id !== undefined) {
      this.subscribeToSaveResponse(this.apPayableService.update(apPayable));
    } else {
      this.subscribeToSaveResponse(this.apPayableService.create(apPayable));
    }
  }

  private createFromForm(): IApPayable {
    return {
      ...new ApPayable(),
      id: this.editForm.get(['id']).value,
      invoiceDate: this.editForm.get(['invoiceDate']).value,
      invoiceAmount: this.editForm.get(['invoiceAmount']).value,
      invoiceNo: this.editForm.get(['invoiceNo']).value,
      comment: this.editForm.get(['comment']).value,
      isCredit: this.editForm.get(['isCredit']).value,
      isCreditNote: this.editForm.get(['isCreditNote']).value,
      isDispute: this.editForm.get(['isDispute']).value,
      docPath: this.editForm.get(['docPath']).value,
      subTotal: this.editForm.get(['subTotal']).value,
      gst: this.editForm.get(['gst']).value,
      hst: this.editForm.get(['hst']).value,
      pst: this.editForm.get(['pst']).value,
      qst: this.editForm.get(['qst']).value,
      totalAmount: this.editForm.get(['totalAmount']).value,
      balanceDue: this.editForm.get(['balanceDue']).value,
      apPayeeTypeId: this.editForm.get(['apPayeeTypeId']).value,
      apPayeeId: this.editForm.get(['apPayeeId']).value,
      apCategoryTypeId: this.editForm.get(['apCategoryTypeId']).value,
      woPaymentMethodId: this.editForm.get(['woPaymentMethodId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IApPayable>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackApPayeeTypeById(index: number, item: IApPayeeType) {
    return item.id;
  }

  trackApPayeeById(index: number, item: IApPayee) {
    return item.id;
  }

  trackApCategoryTypeById(index: number, item: IApCategoryType) {
    return item.id;
  }

  trackWoPaymentMethodById(index: number, item: IWoPaymentMethod) {
    return item.id;
  }
}
