import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ComparisonHolding } from 'app/shared/model/comparison-holding.model';
import { ComparisonHoldingService } from './comparison-holding.service';
import { ComparisonHoldingComponent } from './comparison-holding.component';
import { ComparisonHoldingDetailComponent } from './comparison-holding-detail.component';
import { ComparisonHoldingUpdateComponent } from './comparison-holding-update.component';
import { ComparisonHoldingDeletePopupComponent } from './comparison-holding-delete-dialog.component';
import { IComparisonHolding } from 'app/shared/model/comparison-holding.model';

@Injectable({ providedIn: 'root' })
export class ComparisonHoldingResolve implements Resolve<IComparisonHolding> {
    constructor(private service: ComparisonHoldingService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IComparisonHolding> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ComparisonHolding>) => response.ok),
                map((comparisonHolding: HttpResponse<ComparisonHolding>) => comparisonHolding.body)
            );
        }
        return of(new ComparisonHolding());
    }
}

export const comparisonHoldingRoute: Routes = [
    {
        path: '',
        component: ComparisonHoldingComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ComparisonHoldings'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ComparisonHoldingDetailComponent,
        resolve: {
            comparisonHolding: ComparisonHoldingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComparisonHoldings'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ComparisonHoldingUpdateComponent,
        resolve: {
            comparisonHolding: ComparisonHoldingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComparisonHoldings'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ComparisonHoldingUpdateComponent,
        resolve: {
            comparisonHolding: ComparisonHoldingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComparisonHoldings'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const comparisonHoldingPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ComparisonHoldingDeletePopupComponent,
        resolve: {
            comparisonHolding: ComparisonHoldingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComparisonHoldings'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
