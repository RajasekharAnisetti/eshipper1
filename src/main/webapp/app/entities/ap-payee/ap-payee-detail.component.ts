import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IApPayee } from 'app/shared/model/ap-payee.model';

@Component({
  selector: 'jhi-ap-payee-detail',
  templateUrl: './ap-payee-detail.component.html'
})
export class ApPayeeDetailComponent implements OnInit {
  apPayee: IApPayee;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ apPayee }) => {
      this.apPayee = apPayee;
    });
  }

  previousState() {
    window.history.back();
  }
}
