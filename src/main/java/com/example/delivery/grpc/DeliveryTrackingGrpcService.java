package com.example.delivery.grpc;

import com.example.delivery.proto.DeliveryTrackingServiceGrpc;

import com.example.delivery.proto.LocationRequest;
import com.example.delivery.proto.LocationResponse;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class DeliveryTrackingGrpcService extends DeliveryTrackingServiceGrpc.DeliveryTrackingServiceImplBase {

    @Override
    public void getCurrentLocation(
            LocationRequest request,
            StreamObserver<LocationResponse> responseObserver
    ) {
        var response = LocationResponse.newBuilder()
                .setDeliveryId(request.getDeliveryId())
                .setLatitude(-23.5505)
                .setLongitude(-46.6333)
                .setTimestamp(System.currentTimeMillis())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void streamLocation(
            LocationRequest request,
            StreamObserver<LocationResponse> responseObserver
    ) {
        try {
            responseObserver.onNext(
                    LocationResponse.newBuilder()
                            .setDeliveryId(request.getDeliveryId())
                            .setLatitude(-23.5505)
                            .setLongitude(-46.6333)
                            .setTimestamp(System.currentTimeMillis())
                            .build()
            );

            Thread.sleep(3000);

            responseObserver.onNext(
                    LocationResponse.newBuilder()
                            .setDeliveryId(request.getDeliveryId())
                            .setLatitude(-23.5500)
                            .setLongitude(-46.6320)
                            .setTimestamp(System.currentTimeMillis())
                            .build()
            );

            Thread.sleep(3000);

            responseObserver.onNext(
                    LocationResponse.newBuilder()
                            .setDeliveryId(request.getDeliveryId())
                            .setLatitude(-23.5495)
                            .setLongitude(-46.6310)
                            .setTimestamp(System.currentTimeMillis())
                            .build()
            );

            Thread.sleep(3000);

            responseObserver.onNext(
                    LocationResponse.newBuilder()
                            .setDeliveryId(request.getDeliveryId())
                            .setLatitude(-23.5490)
                            .setLongitude(-46.6300)
                            .setTimestamp(System.currentTimeMillis())
                            .build()
            );
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            responseObserver.onError(e);
        }

        responseObserver.onCompleted();
    }
}
