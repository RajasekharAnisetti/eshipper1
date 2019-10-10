import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApPayeeType } from 'app/shared/model/ap-payee-type.model';
import { ApPayeeTypeService } from './ap-payee-type.service';

@Component({
  selector: 'jhi-ap-payee-type-delete-dialog',
  templateUrl: './ap-payee-type-delete-dialog.component.html'
})
export class ApPayeeTypeDeleteDialogComponent {
  apPayeeType: IApPayeeType;

  constructor(
    protected apPayeeTypeService: ApPayeeTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.apPayeeTypeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'apPayeeTypeListModification',
        content: 'Deleted an apPayeeType'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-ap-payee-type-delete-popup',
  template: ''
})
export class ApPayeeTypeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ apPayeeType }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ApPayeeTypeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.apPayeeType = apPayeeType;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/ap-payee-type', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/ap-payee-type', { outlets: { popup: null } }]);
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
