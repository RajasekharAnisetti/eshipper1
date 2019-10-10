import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IApPayableCreditNotesTrans, ApPayableCreditNotesTrans } from 'app/shared/model/ap-payable-credit-notes-trans.model';
import { ApPayableCreditNotesTransService } from './ap-payable-credit-notes-trans.service';
import { IApPayable } from 'app/shared/model/ap-payable.model';
import { ApPayableService } from 'app/entities/ap-payable/ap-payable.service';
import { IApPayee } from 'app/shared/model/ap-payee.model';
import { ApPayeeService } from 'app/entities/ap-payee/ap-payee.service';

@Component({
  selector: 'jhi-ap-payable-credit-notes-trans-update',
  templateUrl: './ap-payable-credit-notes-trans-update.component.html'
})
export class ApPayableCreditNotesTransUpdateComponent implements OnInit {
  isSaving: boolean;

  appayables: IApPayable[];

  appayees: IApPayee[];

  editForm = this.fb.group({
    id: [],
    type: [null, [Validators.maxLength(255)]],
    amount: [null, [Validators.max(11)]],
    apPayableId: [],
    apPayeeId: [],
    apPayableId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected apPayableCreditNotesTransService: ApPayableCreditNotesTransService,
    protected apPayableService: ApPayableService,
    protected apPayeeService: ApPayeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ apPayableCreditNotesTrans }) => {
      this.updateForm(apPayableCreditNotesTrans);
    });
    this.apPayableService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IApPayable[]>) => mayBeOk.ok),
        map((response: HttpResponse<IApPayable[]>) => response.body)
      )
      .subscribe((res: IApPayable[]) => (this.appayables = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.apPayeeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IApPayee[]>) => mayBeOk.ok),
        map((response: HttpResponse<IApPayee[]>) => response.body)
      )
      .subscribe((res: IApPayee[]) => (this.appayees = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(apPayableCreditNotesTrans: IApPayableCreditNotesTrans) {
    this.editForm.patchValue({
      id: apPayableCreditNotesTrans.id,
      type: apPayableCreditNotesTrans.type,
      amount: apPayableCreditNotesTrans.amount,
      apPayableId: apPayableCreditNotesTrans.apPayableId,
      apPayeeId: apPayableCreditNotesTrans.apPayeeId,
      apPayableId: apPayableCreditNotesTrans.apPayableId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const apPayableCreditNotesTrans = this.createFromForm();
    if (apPayableCreditNotesTrans.id !== undefined) {
      this.subscribeToSaveResponse(this.apPayableCreditNotesTransService.update(apPayableCreditNotesTrans));
    } else {
      this.subscribeToSaveResponse(this.apPayableCreditNotesTransService.create(apPayableCreditNotesTrans));
    }
  }

  private createFromForm(): IApPayableCreditNotesTrans {
    return {
      ...new ApPayableCreditNotesTrans(),
      id: this.editForm.get(['id']).value,
      type: this.editForm.get(['type']).value,
      amount: this.editForm.get(['amount']).value,
      apPayableId: this.editForm.get(['apPayableId']).value,
      apPayeeId: this.editForm.get(['apPayeeId']).value,
      apPayableId: this.editForm.get(['apPayableId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IApPayableCreditNotesTrans>>) {
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

  trackApPayableById(index: number, item: IApPayable) {
    return item.id;
  }

  trackApPayeeById(index: number, item: IApPayee) {
    return item.id;
  }
}
