import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApCategoryType } from 'app/shared/model/ap-category-type.model';
import { ApCategoryTypeService } from './ap-category-type.service';

@Component({
  selector: 'jhi-ap-category-type-delete-dialog',
  templateUrl: './ap-category-type-delete-dialog.component.html'
})
export class ApCategoryTypeDeleteDialogComponent {
  apCategoryType: IApCategoryType;

  constructor(
    protected apCategoryTypeService: ApCategoryTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.apCategoryTypeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'apCategoryTypeListModification',
        content: 'Deleted an apCategoryType'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-ap-category-type-delete-popup',
  template: ''
})
export class ApCategoryTypeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ apCategoryType }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ApCategoryTypeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.apCategoryType = apCategoryType;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/ap-category-type', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/ap-category-type', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
