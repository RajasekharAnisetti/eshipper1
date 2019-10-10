import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IApPayeeType, ApPayeeType } from 'app/shared/model/ap-payee-type.model';
import { ApPayeeTypeService } from './ap-payee-type.service';

@Component({
  selector: 'jhi-ap-payee-type-update',
  templateUrl: './ap-payee-type-update.component.html'
})
export class ApPayeeTypeUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]]
  });

  constructor(protected apPayeeTypeService: ApPayeeTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ apPayeeType }) => {
      this.updateForm(apPayeeType);
    });
  }

  updateForm(apPayeeType: IApPayeeType) {
    this.editForm.patchValue({
      id: apPayeeType.id,
      name: apPayeeType.name
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const apPayeeType = this.createFromForm();
    if (apPayeeType.id !== undefined) {
      this.subscribeToSaveResponse(this.apPayeeTypeService.update(apPayeeType));
    } else {
      this.subscribeToSaveResponse(this.apPayeeTypeService.create(apPayeeType));
    }
  }

  private createFromForm(): IApPayeeType {
    return {
      ...new ApPayeeType(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IApPayeeType>>) {
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
