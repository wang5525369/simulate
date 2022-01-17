package com.wangxb.config;

import com.wangxb.service.BService;
import com.wangxb.service.DService;
import org.springframework.asm.ClassWriter;
import org.springframework.asm.FieldVisitor;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Opcodes;

import static org.springframework.asm.Opcodes.*;

public class CustomBean {
    public byte[] createBean(String className) throws IllegalAccessException, InstantiationException{
        String tempClassName = className.replace('.','/');
        String superName = "java/lang/Object"; //"com/wangxb/service/DService";
        String [] interfaceName = {"com/wangxb/service/CService"};
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        cw.visitAnnotation("Lorg/springframework/stereotype/Service",false);
        cw.visit(Opcodes.V1_8, ACC_PUBLIC, tempClassName, null, superName, interfaceName);
        FieldVisitor fieldVisitor = cw.visitField(ACC_PUBLIC + ACC_FINAL,"i","I",null,new Integer(2));
        MethodVisitor constructor = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        constructor.visitVarInsn(Opcodes.ALOAD, 0);
        constructor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        constructor.visitVarInsn(Opcodes.ALOAD, 0);
        constructor.visitVarInsn(BIPUSH,123);
        constructor.visitFieldInsn(PUTFIELD, tempClassName, "i", "I");
        constructor.visitInsn(Opcodes.RETURN);
        constructor.visitMaxs(1, 1);
        constructor.visitEnd();

        MethodVisitor methodVisitor = cw.visitMethod(ACC_PUBLIC, "getB", "()I", null, null);
        methodVisitor.visitInsn(ICONST_5);
        methodVisitor.visitInsn(IRETURN);
        methodVisitor.visitEnd();
        cw.visitEnd();
        byte[] classByte = cw.toByteArray();

        return classByte;
    }

    public byte[] createInterface(){
        String className = "com.wangxb.service.BService";
        String tempClassName = className.replace('.','/');
        String superName = "java/lang/Object";
        String [] interfaceName = {"com/wangxb/service/CService"};
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        cw.visitAnnotation("Lorg/springframework/stereotype/Service",false);
        cw.visit(Opcodes.V1_8, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, tempClassName, null, superName, interfaceName);
        FieldVisitor fieldVisitor = cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,"i","I",null,123);
        fieldVisitor.visitEnd();
        cw.visitEnd();
        byte[] classByte = cw.toByteArray();
        return classByte;
    }

    public byte [] convertBean(String beanPath){
        return null;
    }

}
