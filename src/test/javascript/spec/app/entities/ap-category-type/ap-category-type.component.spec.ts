import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Eshipper1TestModule } from '../../../test.module';
import { ApCategoryTypeComponent } from 'app/entities/ap-category-type/ap-category-type.component';
import { ApCategoryTypeService } from 'app/entities/ap-category-type/ap-category-type.service';
import { ApCategoryType } from 'app/shared/model/ap-category-type.model';

describe('Component Tests', () => {
  describe('ApCategoryType Management Component', () => {
    let comp: ApCategoryTypeComponent;
    let fixture: ComponentFixture<ApCategoryTypeComponent>;
    let service: ApCategoryTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApCategoryTypeComponent],
        providers: []
      })
        .overrideTemplate(ApCategoryTypeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApCategoryTypeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApCategoryTypeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ApCategoryType(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.apCategoryTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
