import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Eshipper1TestModule } from '../../../test.module';
import { ApCategoryTypeDetailComponent } from 'app/entities/ap-category-type/ap-category-type-detail.component';
import { ApCategoryType } from 'app/shared/model/ap-category-type.model';

describe('Component Tests', () => {
  describe('ApCategoryType Management Detail Component', () => {
    let comp: ApCategoryTypeDetailComponent;
    let fixture: ComponentFixture<ApCategoryTypeDetailComponent>;
    const route = ({ data: of({ apCategoryType: new ApCategoryType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApCategoryTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ApCategoryTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApCategoryTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.apCategoryType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
