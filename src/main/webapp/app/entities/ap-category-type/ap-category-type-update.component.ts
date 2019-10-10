import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IApCategoryType, ApCategoryType } from 'app/shared/model/ap-category-type.model';
import { ApCategoryTypeService } from './ap-category-type.service';

@Component({
  selector: 'jhi-ap-category-type-update',
  templateUrl: './ap-category-type-update.component.html'
})
export class ApCategoryTypeUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]]
  });

  constructor(protected apCategoryTypeService: ApCategoryTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ apCategoryType }) => {
      this.updateForm(apCategoryType);
    });
  }

  updateForm(apCategoryType: IApCategoryType) {
    this.editForm.patchValue({
      id: apCategoryType.id,
      name: apCategoryType.name
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const apCategoryType = this.createFromForm();
    if (apCategoryType.id !== undefined) {
      this.subscribeToSaveResponse(this.apCategoryTypeService.update(apCategoryType));
    } else {
      this.subscribeToSaveResponse(this.apCategoryTypeService.create(apCategoryType));
    }
  }

  private createFromForm(): IApCategoryType {
    return {
      ...new ApCategoryType(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IApCategoryType>>) {
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
