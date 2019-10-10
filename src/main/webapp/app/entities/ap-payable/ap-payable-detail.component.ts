import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IApPayable } from 'app/shared/model/ap-payable.model';

@Component({
  selector: 'jhi-ap-payable-detail',
  templateUrl: './ap-payable-detail.component.html'
})
export class ApPayableDetailComponent implements OnInit {
  apPayable: IApPayable;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ apPayable }) => {
      this.apPayable = apPayable;
    });
  }

  previousState() {
    window.history.back();
  }
}
