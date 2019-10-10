import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IApPayableCreditNotesTrans } from 'app/shared/model/ap-payable-credit-notes-trans.model';

@Component({
  selector: 'jhi-ap-payable-credit-notes-trans-detail',
  templateUrl: './ap-payable-credit-notes-trans-detail.component.html'
})
export class ApPayableCreditNotesTransDetailComponent implements OnInit {
  apPayableCreditNotesTrans: IApPayableCreditNotesTrans;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ apPayableCreditNotesTrans }) => {
      this.apPayableCreditNotesTrans = apPayableCreditNotesTrans;
    });
  }

  previousState() {
    window.history.back();
  }
}
