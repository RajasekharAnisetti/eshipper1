import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IWoPaymentMethod, WoPaymentMethod } from 'app/shared/model/wo-payment-method.model';
import { WoPaymentMethodService } from './wo-payment-method.service';

@Component({
  selector: 'jhi-wo-payment-method-update',
  templateUrl: './wo-payment-method-update.component.html'
})
export class WoPaymentMethodUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]]
  });

  constructor(
    protected woPaymentMethodService: WoPaymentMethodService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ woPaymentMethod }) => {
      this.updateForm(woPaymentMethod);
    });
  }

  updateForm(woPaymentMethod: IWoPaymentMethod) {
    this.editForm.patchValue({
      id: woPaymentMethod.id,
      name: woPaymentMethod.name
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const woPaymentMethod = this.createFromForm();
    if (woPaymentMethod.id !== undefined) {
      this.subscribeToSaveResponse(this.woPaymentMethodService.update(woPaymentMethod));
    } else {
      this.subscribeToSaveResponse(this.woPaymentMethodService.create(woPaymentMethod));
    }
  }

  private createFromForm(): IWoPaymentMethod {
    return {
      ...new WoPaymentMethod(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoPaymentMethod>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
