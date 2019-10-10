import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IApPayee, ApPayee } from 'app/shared/model/ap-payee.model';
import { ApPayeeService } from './ap-payee.service';

@Component({
  selector: 'jhi-ap-payee-update',
  templateUrl: './ap-payee-update.component.html'
})
export class ApPayeeUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]]
  });

  constructor(protected apPayeeService: ApPayeeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ apPayee }) => {
      this.updateForm(apPayee);
    });
  }

  updateForm(apPayee: IApPayee) {
    this.editForm.patchValue({
      id: apPayee.id,
      name: apPayee.name
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const apPayee = this.createFromForm();
    if (apPayee.id !== undefined) {
      this.subscribeToSaveResponse(this.apPayeeService.update(apPayee));
    } else {
      this.subscribeToSaveResponse(this.apPayeeService.create(apPayee));
    }
  }

  private createFromForm(): IApPayee {
    return {
      ...new ApPayee(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IApPayee>>) {
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
