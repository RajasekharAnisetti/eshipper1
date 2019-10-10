import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApPayableCreditNotesTrans } from 'app/shared/model/ap-payable-credit-notes-trans.model';
import { ApPayableCreditNotesTransService } from './ap-payable-credit-notes-trans.service';

@Component({
  selector: 'jhi-ap-payable-credit-notes-trans-delete-dialog',
  templateUrl: './ap-payable-credit-notes-trans-delete-dialog.component.html'
})
export class ApPayableCreditNotesTransDeleteDialogComponent {
  apPayableCreditNotesTrans: IApPayableCreditNotesTrans;

  constructor(
    protected apPayableCreditNotesTransService: ApPayableCreditNotesTransService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.apPayableCreditNotesTransService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'apPayableCreditNotesTransListModification',
        content: 'Deleted an apPayableCreditNotesTrans'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-ap-payable-credit-notes-trans-delete-popup',
  template: ''
})
export class ApPayableCreditNotesTransDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ apPayableCreditNotesTrans }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ApPayableCreditNotesTransDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.apPayableCreditNotesTrans = apPayableCreditNotesTrans;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/ap-payable-credit-notes-trans', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/ap-payable-credit-notes-trans', { outlets: { popup: null } }]);
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
