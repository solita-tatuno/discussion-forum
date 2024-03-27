import { ReactNode } from "react";
import { createPortal } from "react-dom";

interface Props {
  children: ReactNode;
  isOpen: boolean;
  onClose: () => void;
}

function Modal({ isOpen, onClose, children }: Props) {
  if (!isOpen) return null;

  return createPortal(
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-gray-600 bg-opacity-20">
      <div className="w-full max-w-sm rounded-lg bg-white p-4">
        <button
          className="right-0 top-0 float-right text-xl font-semibold"
          onClick={onClose}
        >
          &times;
        </button>
        {children}
      </div>
    </div>,
    document.body
  );
}

export default Modal;
