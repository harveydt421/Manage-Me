/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ManageMeTestModule } from '../../../test.module';
import { SeparationApplicationComponent } from 'app/entities/separation-application/separation-application.component';
import { SeparationApplicationService } from 'app/entities/separation-application/separation-application.service';
import { SeparationApplication } from 'app/shared/model/separation-application.model';

describe('Component Tests', () => {
    describe('SeparationApplication Management Component', () => {
        let comp: SeparationApplicationComponent;
        let fixture: ComponentFixture<SeparationApplicationComponent>;
        let service: SeparationApplicationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManageMeTestModule],
                declarations: [SeparationApplicationComponent],
                providers: []
            })
                .overrideTemplate(SeparationApplicationComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SeparationApplicationComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SeparationApplicationService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SeparationApplication(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.separationApplications[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
